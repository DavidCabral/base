package br.com.modelo.util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Util {


  public static String getURL(int porta){
    return String.format(Constantes.URL,porta);
  }


  public synchronized static String getVersion() {
    // Try to get version number from pom.xml (available in Eclipse)
    try {
      String className = Util.class.getName();
      String classfileName = "/" + className.replace('.', '/') + ".class";
      URL classfileResource = Util.class.getResource(classfileName);
      if (classfileResource != null) {
        Path absolutePackagePath = Paths.get(classfileResource.toURI()).getParent();
        int packagePathSegments = className.length() - className.replace(".", "").length();
        // Remove package segments from path, plus two more levels
        // for "target/classes", which is the standard location for
        // classes in Eclipse.
        Path path = absolutePackagePath;
        for (int i = 0, segmentsToRemove = packagePathSegments + 2; i < segmentsToRemove; i++) {
          path = path.getParent();
        }
        Path pom = path.resolve("pom.xml");
        try (InputStream is = Files.newInputStream(pom)) {
          Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
          doc.getDocumentElement().normalize();
          String version = (String) XPathFactory.newInstance().newXPath()
              .compile("/project/version").evaluate(doc, XPathConstants.STRING);
          if (version != null) {
            version = version.trim();
            if (!version.isEmpty()) {
              return version;
            }
          }
        }
      }
    } catch (Exception e) {
      // Ignore
    }

    // Try to get version number from maven properties in jar's META-INF
    try (InputStream is =
        Util.class.getResourceAsStream("/META-INF/maven/jar/api/pom.properties")) {
      if (is != null) {
        Properties p = new Properties();
        p.load(is);
        String version = p.getProperty("version", "").trim();
        if (!version.isEmpty()) {
          return version;
        }
      }
    } catch (Exception e) {
      // Ignore
    }

    // Fallback to using Java API to get version from MANIFEST.MF
    String version = null;
    Package pkg = Util.class.getPackage();
    if (pkg != null) {
      version = pkg.getImplementationVersion();
      if (version == null) {
        version = pkg.getSpecificationVersion();
      }
    }
    version = version == null ? "" : version.trim();
    return version.isEmpty() ? "unknown" : version;
  }



}
