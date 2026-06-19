package com.example.composeactivity.utils

import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import java.text.Normalizer

public class Utils {
    companion object {

        private val diacriticRegex = "\\p{InCombiningDiacriticalMarks}+".toRegex()
        private val nonAlphanumericRegex = "[^A-Za-z0-9]+".toRegex()

        fun normalizeName(name: String): String =
            Normalizer.normalize(name, Normalizer.Form.NFD)
                .replace(diacriticRegex, "")
                .replace(nonAlphanumericRegex, "")
                .lowercase()

        fun isNameOnList(name: String, list: List<Object>): Boolean {

            val castedList = when {
                list.all { it is Specjalization } ->
                    list.filterIsInstance<Specjalization>()
                list.all { it is Examination } ->
                    list.filterIsInstance<Examination>()
                else -> return true
            }


            val normalizedNew = normalizeName(name)
            return castedList.any {
                normalizeName(it.name) == normalizedNew
            }
        }

    }
}