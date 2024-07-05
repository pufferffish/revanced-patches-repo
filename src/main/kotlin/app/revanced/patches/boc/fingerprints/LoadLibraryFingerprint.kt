package app.revanced.patches.boc.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.NarrowLiteralInstruction
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

internal object LoadLibraryFingerprint : MethodFingerprint(
    returnType = "V",
    parameters = listOf(),
    customFingerprint = { method, clazz ->
        clazz.methods.any {
            m -> AccessFlags.NATIVE.isSet(m.accessFlags)
        } &&
        method.implementation?.instructions?.any { inst ->
            if (inst.opcode != Opcode.INVOKE_STATIC) return@any false
            val reference = (inst as ReferenceInstruction).reference as MethodReference
            reference.definingClass == "Ljava/lang/System;" && reference.name == "loadLibrary"
        }?: false &&
        method.implementation?.instructions?.any { inst ->
            if (inst.opcode != Opcode.CONST_16) return@any false
            val literal = (inst as NarrowLiteralInstruction).narrowLiteral
            literal == 9
        }?: false
    }
)