package app.revanced.patches.example.boc

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.example.boc.fingerprints.LoadLibraryFingerprint

@Patch(
    name = "BOC Patch",
    description = "Bypass BOC security restrictions.",
    compatiblePackages = [
        CompatiblePackage("com.bochk.app.aos"),
    ],
)
@Suppress("unused")
object BOCPatch : BytecodePatch(setOf(LoadLibraryFingerprint)) {
    override fun execute(context: BytecodeContext) {
        val loadLibraryClass = LoadLibraryFingerprint.result?.mutableClass!!
        println("Found loadLibrary class: $loadLibraryClass")
        loadLibraryClass.methods
            .filter { m -> m.returnType == "V" && m.implementation != null && !setOf("<clinit>", "<init>").contains(m.name) }
            .forEach { m ->
                m.addInstructions(0, "return-void")
                println("Neutered $m")
            }
    }
}
