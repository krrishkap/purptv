package tv.purple.monolith.core.util

import java.io.File
import java.util.UUID


object FileUtil {
    private const val COPY_BUFFER_SIZE = 8 * 1024

    fun File.copyTo(outFile: File) {
        this.inputStream().use { i ->
            outFile.outputStream().use { output ->
                i.copyTo(output, COPY_BUFFER_SIZE)
            }
        }
    }

    fun File.deleteDir() {
        deleteRecursive(this)
    }

    fun File.dirSize(): Long {
        if (isFile) {
            return length()
        }
        var size = 0L
        for (child in this.listFiles() ?: emptyArray()) {
            size += child.dirSize()
        }

        return size
    }

    private fun deleteRecursive(fileOrDirectory: File) {
        for (child in fileOrDirectory.listFiles() ?: emptyArray()) {
            deleteRecursive(child)
        }

        fileOrDirectory.delete()
    }

    fun String.fromHexToUUID(): UUID {
        if (this.length != 32) {
            throw IllegalArgumentException("Hex string must be 32 characters")
        }

        val mostSigBits = java.lang.Long.parseUnsignedLong(this.substring(0, 16), 16)
        val leastSigBits = java.lang.Long.parseUnsignedLong(this.substring(16), 16)

        return UUID(mostSigBits, leastSigBits)
    }

    fun UUID.toHex(): String {
        return this.toString().replace("-", "").lowercase()
    }
}