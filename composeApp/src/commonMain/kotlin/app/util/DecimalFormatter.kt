package app.util

object DecimalFormatter {
    private const val decimalSeparator = '.'

    fun format(input: String): String {

        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()

        var hasDecimalSep = false
        var charsAfterSep = 0

        for (char in input) {
            if (charsAfterSep > 1) continue
            if (char.isDigit()) {
                if (hasDecimalSep) {
                    charsAfterSep++
                }
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true
            }
        }

        return sb.toString()
    }
}