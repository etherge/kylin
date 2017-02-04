/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.apache.kylin.common.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.kylin.common.KylinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ysong1
 */
public class HBaseMetadataTestCase extends AbstractKylinTestCase {
    private static final Logger logger = LoggerFactory.getLogger(HBaseMetadataTestCase.class);
    public static String SANDBOX_TEST_DATA = "../examples/test_case_data/sandbox/";

    static {
        try {
            String hadoopenv = System.getProperty("hadoopenv");
            if(StringUtils.isEmpty(hadoopenv)){
                logger.warn("No hdpenv set; Please set hadoopenv in your jvm option, for example: -Dhadoopenv=hdp2.4");
                hadoopenv = "hdp2.4";
            }
            SANDBOX_TEST_DATA += hadoopenv;
            ClassUtil.addClasspath(new File(SANDBOX_TEST_DATA).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTestMetadata() throws Exception {
        staticCreateTestMetadata();
    }

    @Override
    public void cleanupTestMetadata() {
        staticCleanupTestMetadata();
    }

    public static void staticCreateTestMetadata() throws Exception {
        staticCreateTestMetadata(SANDBOX_TEST_DATA);
    }

    public static void staticCreateTestMetadata(String kylinConfigFolder) {

        KylinConfig.destroyInstance();

        if (System.getProperty(KylinConfig.KYLIN_CONF) == null && System.getenv(KylinConfig.KYLIN_CONF) == null)
            System.setProperty(KylinConfig.KYLIN_CONF, kylinConfigFolder);

    }

}
