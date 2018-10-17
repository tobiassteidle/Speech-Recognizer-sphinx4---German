package com.example;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class LiveSpeechDemo {

    public static void main(String[] args) throws Exception {
        final Configuration configuration = new Configuration();

        // Set path to acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/de-DE/de-DE");

        // Set path to dictionary.
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/de-DE/cmusphinx-voxforge-de.dic");

        // Set language model.
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/de-DE/cmusphinx-voxforge-de.lm.bin");

        configuration.setSampleRate(8000);

        final LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        // Start recognition process pruning previously cached data.
        recognizer.startRecognition(true);

        SpeechResult result = recognizer.getResult();

        while ((result = recognizer.getResult()) != null) {
            System.out.println(result.getHypothesis());

            // Get individual words and their times.
            for (final WordResult r : result.getWords()) {
                System.out.println(r);
            }
        }

        // Pause recognition process. It can be resumed then with startRecognition(false).
        recognizer.stopRecognition();
    }
}
