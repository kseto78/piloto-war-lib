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

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * @author stepio
 */
public class AssertZip {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssertZip.class);
    private static final String ERROR_BAD_ZIP = "Failed to open zip file";

    public static void assertValid(final File file) {
        if (!isValid(file)) {
            Assert.fail(ERROR_BAD_ZIP);
        }
    }

    private static boolean isValid(final File file) {
        try (ZipFile zip = new ZipFile(file)) {
            return true;
        } catch (IOException e) {
            LOGGER.error(ERROR_BAD_ZIP, e);
            return false;
        }
    }
}
