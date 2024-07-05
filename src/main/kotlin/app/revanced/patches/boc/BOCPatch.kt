package app.revanced.patches.boc

import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.hkbank.BypassBankPatch
import app.revanced.patches.hkbank.fingerprints.LoadLibraryFingerprint

@Patch(
    name = "BOC Patch",
    description = "Bypass BOC security restrictions.",
    compatiblePackages = [
        CompatiblePackage("com.bochk.app.aos"),
    ],
)
@Suppress("unused")
object BOCPatch : BypassBankPatch(LoadLibraryFingerprint("bochk_aos"))
