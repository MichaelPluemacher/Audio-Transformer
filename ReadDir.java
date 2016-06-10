package ListFileNames;

/**
 * Get a list of all the audio files to be transformed
 */

import java.io.File;

import java.io.IOException;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class ReadDir {

    public List<File> GetNames(String path) throws IOException {
        // dir: path where to start looking for files including in all subdirectories
        // filter: get only audio files, i.e. those with ending .wav
        // TrueFileFilter.INSTANCE: always True, i.e. parse all subdirectories
        File dir = new File(path);
        IOFileFilter filter;
        filter = new SuffixFileFilter(".wav");

        System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");

        return (List<File>) FileUtils.listFiles(dir, filter, TrueFileFilter.INSTANCE);
    }

}