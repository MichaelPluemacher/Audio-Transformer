/**
 * A small utility to transform ripped CDs, i.e. .wav files
 * into a format more suited for mobile devices, e.g. .flac
 */

import ListFileNames.ReadDir;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.apache.commons.io.FilenameUtils;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;


public class AudioTransform{

    public static void main(String args[]) throws IOException  {

        ReadDir listOfFiles = new ReadDir();

        String originalPath = "D:/choral/";  // where the .wav files are located
        String newPath = "D:/Choral_wma/";   // where the transformed files are to be saved

        List<File> files = listOfFiles.GetNames(originalPath);

        System.out.println(files);

        for (File file : files) {
            Path pathAbsolute = Paths.get(file.getCanonicalPath());
            Path pathBase = Paths.get(originalPath);
            Path pathRelative = pathBase.relativize(pathAbsolute);
            Path targetPath = Paths.get(newPath +
                    "\\" +
                    FilenameUtils.removeExtension(pathRelative.toString()) +
                    ".flac");
            System.out.println("processing: " + pathAbsolute);
            File source = new File(pathAbsolute.toString());
            File target = new File(targetPath.toString());
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("flac");
            //audio.setBitRate(new Integer(128000)); //bitrate value for the new re-encoded audio stream
            //audio.setChannels(new Integer(2));  // number of channels 2: stereo
            //audio.setSamplingRate(new Integer(44100));  //sampling rate for the new re-encoded audio stream
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("flac");
            attrs.setAudioAttributes(audio);
            Encoder encoder = new Encoder();
            try {
                encoder.encode(source, target, attrs);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InputFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (EncoderException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            }


    }

}
