package noe.tomcat

import noe.common.DefaultProperties
import noe.common.utils.Java
import noe.common.utils.Platform
import org.junit.Assume
import org.junit.BeforeClass

class JvmRouteTomcat9ConfiguratorIT extends JvmRouteTomcatConfiguratorIT {

  @BeforeClass
  public static void beforeClass() {
    Platform platform = new Platform()
    Assume.assumeFalse("JWS is not supported on HP-UX => skipping", platform.isHP())
    Assume.assumeTrue("JWS 5.0 is officially supported on RHEL versions lower than 10", platform.OSVersionLessThan(10));
    Assume.assumeTrue("Tomcat from JWS 5.0 requires at least Java 1.8",
            (DefaultProperties.SERVER_JAVA_HOME?.contains('jdk1.8')) ?: Java.isJdkXOrHigher('1.8')
    )

    loadTestProperties("/tomcat9-common-test.properties")
    prepareWorkspace()
  }

}
