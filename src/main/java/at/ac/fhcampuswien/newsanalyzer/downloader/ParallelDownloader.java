package at.ac.fhcampuswien.newsanalyzer.downloader;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        if(urls == null || urls.isEmpty()){
            return 0;
        }
        // todo
        urls.forEach(this::saveUrl);

        return urls.size();
    }

    private void saveUrl(String url){
        int numWorkers = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(numWorkers);
        pool.execute(() -> saveUrl2File(url));
    }

}
