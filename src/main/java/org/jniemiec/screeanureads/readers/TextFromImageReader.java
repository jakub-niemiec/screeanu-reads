package org.jniemiec.screeanureads.readers;

import java.io.File;

@FunctionalInterface
public interface TextFromImageReader {

    String read(File file);
}
