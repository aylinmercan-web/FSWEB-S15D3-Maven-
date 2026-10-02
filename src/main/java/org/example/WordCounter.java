package org.example;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WordCounter {

    private static final String TEXT =
            "When the offensive resumed, the Turks received their first victory when the Greeks encountered stiff resistance in the battles of First and Second İnönü," +
            " due to İsmet Pasha's organization of an irregular militia into a regular army. " +
            " The two victories led to Allied proposals to amend the Treaty of Sèvres where both Ankara and Istanbul were represented, but Greece refused." +
            " With the conclusion of the Southern and Eastern fronts, Ankara was able to concentrate more forces on the West against the Greeks." +
            " They also began to receive support from Soviet Union, as well as France and Italy, who sought to check British influence in the Near East.\n" +
            " June–July 1921 saw heavy fighting in the Battle of Kütahya-Eskişehir. While it was an eventual Greek victory, the Turkish army withdrew in good order to the Sakarya river, their last line of defence." +
            " Mustafa Kemal Pasha replaced İsmet Pasha after the defeat as commander in chief as well as his political duties." +
            " The decision was made in the Greek military command to march on the nationalist capital of Ankara to force Mustafa Kemal to the negotiating table." +
            " For 21 days, the Turks and Greeks fought a pitched battle at the Sakarya river, which ended in Greek withdrawal." +
            " Almost of year of stalemate without much fighting followed, during which Greek moral and discipline faltered while Turkish strength increased." +
            " French and Italian forces evacuated from Anatolia. The Allies offered an armistice to the Turks, which Mustafa Kemal refused.";

    /**
     * Metindeki her kelimenin kaç kez geçtiğini döner.
     * Metin tek geçişte taranır, map işlemleri O(1) olduğu için toplam O(n).
     * Kelimeler küçük harfe çevrilir; noktalama işaretleri atılır.
     */
    public static Map<String, Integer> calculateWord() {
        Map<String, Integer> wordCounts = new HashMap<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < TEXT.length(); i++) {
            char c = TEXT.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else if (Character.isWhitespace(c) || c == '-' || c == '–') {
                addWord(wordCounts, word);
            }
            // Diğer işaretler (virgül, nokta, kesme işareti) yok sayılır: "Pasha's" -> "pashas"
        }
        addWord(wordCounts, word);

        return wordCounts;
    }

    // Testler metodu bu isimle çağırıyor (README'de calculateWord yazıyor).
    public static Map<String, Integer> calculatedWord() {
        return calculateWord();
    }

    private static void addWord(Map<String, Integer> wordCounts, StringBuilder word) {
        if (word.length() == 0) {
            return;
        }
        String key = word.toString().toLowerCase(Locale.ENGLISH);
        wordCounts.merge(key, 1, Integer::sum);
        word.setLength(0);
    }
}
