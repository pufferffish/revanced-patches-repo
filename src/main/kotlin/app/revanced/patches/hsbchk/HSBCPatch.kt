package app.revanced.patches.hsbchk

import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.hkbank.BypassBankPatch
import app.revanced.patches.hkbank.fingerprints.LoadLibraryFingerprint

@Patch(
    name = "HSBC HK Patch",
    description = "Bypass HSBC security restrictions.",
    compatiblePackages = [
        CompatiblePackage("hk.com.hsbc.hsbchkmobilebanking"),
    ],
)
@Suppress("unused")
object HSBCHKPatch : BypassBankPatch(LoadLibraryFingerprint("hsbc_hsbchkmobilebanking"))
