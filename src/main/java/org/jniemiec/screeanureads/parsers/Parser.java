package org.jniemiec.screeanureads.parsers;

import java.io.File;

public interface Parser<T> {

    T parse(String input, File file);
}
