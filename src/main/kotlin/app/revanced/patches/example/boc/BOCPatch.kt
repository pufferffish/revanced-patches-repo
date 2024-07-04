package app.revanced.patches.example.boc

import app.revanced.patcher.data.BytecodeContext
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
object BOCPatch : BytecodePatch(emptySet()) {
    override fun execute(context: BytecodeContext) {
        val loadLibraryMethod = LoadLibraryFingerprint.result?.mutableMethod
        println(loadLibraryMethod?.definingClass + "#" + loadLibraryMethod?.name)
    }
}
