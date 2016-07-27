package com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Provides a entry point for the sentence processor.
 */
public class Service {
    public static void main(String[] args) throws Exception {
        // ToDo: use IOC container
        ISentenceParser sentenceParser = new SentenceParser(new ResourceReader());
        INameEntityFinder nounFinder = new NameEntityFinder();
        INameEntityExtractor nounExtractor = new NameEntityExtractor(new ResourceReader());
        String[] nameEntities = nounExtractor.extractNameEntitiesFromFile("NER.txt");
        JAXBContext jc = JAXBContext.newInstance(Content.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String[] fileNames = new String[] { "d01", "d02", "d03", "d04", "d05", "d06", "d07", "d08", "d09", "d10" };
        Content content = new Content();
        List<SentenceFile> files = new LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<SentenceFile>> futureList = new LinkedList<>();

        for (String fileName : fileNames) {
            Callable<SentenceFile> workerThread = new WorkerThread(
                    sentenceParser,
                    nameEntities,
                    nounFinder,
                    fileName
            );
            Future<SentenceFile> future = executor.submit(workerThread);
            futureList.add(future);
        }

        do {
            for (Future<SentenceFile> future : futureList) {
                if (future.isDone()) {
                    SentenceFile file = future.get();
                    files.add(file);
                    futureList.remove(future);
                    break;
                }
            }
        } while (futureList.size() > 0);

        executor.shutdown();

        NameEntityFile nameEntityFile = new NameEntityFile();
        nameEntityFile.setFileName("NER.txt");
        nameEntityFile.setNameEntities(nameEntities);
        content.setNameEntityFile(nameEntityFile);

        content.setSentenceFiles(files);
        marshaller.marshal(content, System.out);
    }
}
