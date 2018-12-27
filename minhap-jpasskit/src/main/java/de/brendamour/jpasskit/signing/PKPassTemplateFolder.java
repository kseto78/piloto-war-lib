/**
 * Copyright (C) 2015 Patrice Brend'amour <patrice@brendamour.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.brendamour.jpasskit.signing;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PKPassTemplateFolder implements IPKPassTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PKPassTemplateFolder.class);
    private File templateDir;

    public PKPassTemplateFolder(URL fileUrlOfTemplateDirectory) throws UnsupportedEncodingException {
        this(URLDecoder.decode(fileUrlOfTemplateDirectory.getFile(), "UTF-8"));
    }

    public PKPassTemplateFolder(String pathToTemplateDirectory) {
//        LOGGER.info("Specified template directory: {}", pathToTemplateDirectory);
        templateDir = new File(pathToTemplateDirectory);
    }

    @Override
    public void provisionPassAtDirectory(File tempPassDir) throws IOException {
        FileFilter filter = FileFileFilter.FILE;
        FileUtils.copyDirectory(templateDir, tempPassDir, filter);
    }

    @Override
    public Map<String, ByteBuffer> getAllFiles() throws IOException {
        Map<String, ByteBuffer> allFiles = new HashMap<>();
        String base = templateDir.getCanonicalPath();
        for (File file : FileUtils.listFiles(templateDir, null, true)) {
            byte[] byteArray = IOUtils.toByteArray(new FileInputStream(file));
            String filePath = file.getCanonicalPath().substring(base.length() + 1);
            LOGGER.debug("File's own path: {}", filePath);
            allFiles.put(filePath, ByteBuffer.wrap(byteArray));
        }
        return allFiles;
    }
}
