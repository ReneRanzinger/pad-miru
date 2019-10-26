package com.github.reneranzinger.pad.miru.scripts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PortraitArchiver
{
    public static final Integer ERROR_FILE_THESHOL = 512;

    public static void main(String[] args) throws IOException
    {
        String t_portraitFolder = "D:/Java/MiruFiles/portrait/";
        String t_archiveFile = "D:/Java/MiruFiles/portrait.zip";
        PortraitArchiver.process(t_portraitFolder, t_archiveFile);
    }

    public static void process(String a_portraitFolder, String a_archiveFile) throws IOException
    {
        Path t_pathFolder = Paths.get(a_portraitFolder);
        FileOutputStream t_outputStreamFile = new FileOutputStream(new File(a_archiveFile));
        ZipOutputStream t_outputStreamZip = new ZipOutputStream(t_outputStreamFile);
        Files.walkFileTree(t_pathFolder, new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult visitFile(Path a_file, BasicFileAttributes a_attributs)
                    throws IOException
            {
                if (a_file.toFile().length() > PortraitArchiver.ERROR_FILE_THESHOL)
                {
                    t_outputStreamZip
                            .putNextEntry(new ZipEntry(t_pathFolder.relativize(a_file).toString()));
                    Files.copy(a_file, t_outputStreamZip);
                    t_outputStreamZip.closeEntry();
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path a_directory,
                    BasicFileAttributes a_attributes) throws IOException
            {
                t_outputStreamZip.putNextEntry(
                        new ZipEntry(t_pathFolder.relativize(a_directory).toString() + "/"));
                t_outputStreamZip.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
        t_outputStreamZip.flush();
        t_outputStreamZip.close();
    }

}
