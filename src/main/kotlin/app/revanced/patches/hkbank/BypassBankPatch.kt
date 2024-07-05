package app.revanced.patches.hkbank

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patches.hkbank.fingerprints.LoadLibraryFingerprint

open class BypassBankPatch(val fingerprint: LoadLibraryFingerprint) : BytecodePatch(setOf(fingerprint)) {
    override fun execute(context: BytecodeContext) {
        val loadLibraryClass = fingerprint.result?.mutableClass!!
        println("Found loadLibrary class: $loadLibraryClass")
        loadLibraryClass.methods
            .filter { m -> m.returnType == "V" && m.implementation != null && !setOf("<clinit>", "<init>").contains(m.name) }
            .forEach { m ->
                m.addInstructions(0, "return-void")
                println("Neutered $m")
            }
    }
}
