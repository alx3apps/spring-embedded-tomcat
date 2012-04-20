package ru.concerteza.springtomcat.installer;

import com.izforge.izpack.compiler.CompilerConfig;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Set;

import static org.apache.commons.io.FileUtils.listFiles;
import static ru.concerteza.util.CtzCopyCheckLMUtils.copyDirectoryToDirectory;
import static ru.concerteza.util.CtzCopyCheckLMUtils.copyFile;
import static ru.concerteza.util.CtzCopyCheckLMUtils.copyFileToDirectory;

/**
 * User: alexey
 * Date: 4/19/12
 */


/**
 * Mojo for izpack
 *
 * @goal installer
 * @phase package
 * @requiresDependencyResolution runtime
 *
 */
public class InstallerMojo extends AbstractMojo {
    /**
     * Base directory of compilation process
     *
     * @parameter expression="${installer.baseDir}" default-value="${project.build.directory}/izpack"
     */
    private File baseDir;
    /**
     * Directory of distribution before for packaging
     *
     * @parameter expression="${installer.distDir}" default-value="${project.build.directory}/izpack/dist"
     */
    private File distDir;
    /**
     * Config file
     *
     * @parameter expression="${installer.installFile}" default-value="${project.basedir}/src/main/installer/izpack.xml"
     */
    private File installFile;

    /**
     * Output file (installer)
     *
     * @parameter expression="${installer.outputFile}" default-value="${project.build.directory}/${project.build.finalName}-installer.jar"
     */
    private File outputFile;
    /**
     * Base directory of compilation process
     *
     * @parameter expression="${installer.buildOutputFile}" default-value="${project.build.directory}/izpack/build.log"
     */
    private File buildOutputFile;
    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Building IzPack installer, parameters:\n" +
                "baseDir: '" + baseDir+ "'\n" +
                "distDir: '" + distDir+ "'\n" +
                "installFile: '" + installFile + "'\n" +
                "outputFile: '" + outputFile + "'\n" +
                "buildOutputFile: " + buildOutputFile + "'");
        PrintStream console = System.out;
        try {
            prepareDirs();
            copyLibs();
            copyResources();
            copyLauncher();
            copyInstallerResources();
            // redirect izPack output to file
            PrintStream ps = new PrintStream(new FileOutputStream(buildOutputFile), true, "UTF-8");
            System.setOut(ps);
            runIzPackCompiler();
        } catch (Exception e) {
            throw new MojoFailureException("IzPack error", e);
        } finally {
            System.setOut(console);
        }
    }

    private void prepareDirs() throws IOException {
        createDir(baseDir);
        createDir(distDir);
        createDir(new File(distDir, "logs"));
        createDir(new File(distDir, "work"));
        createDir(new File(distDir, "uninstall"));
    }

    private void createDir(File dir) throws IOException {
         if (dir.exists()) {
            if (dir.isFile()) throw new IOException("Dir IO error: " + dir.getAbsolutePath());
        } else {
            boolean res = dir.mkdirs();
            if (!res) throw new IOException("Cannot create dir: " + dir.getAbsolutePath());
        }
    }

    @SuppressWarnings("unchecked")
    public void copyLibs() throws IOException {
        File lib = new File(distDir, "lib");
        Set<Artifact> artifacts = project.getArtifacts();
        for(Artifact ar : artifacts) {
            copyFileToDirectory(ar.getFile(), lib);
        }
    }

    private void copyResources() throws IOException {
        File bin = new File(project.getBasedir(), "src/main/bin");
        copyDirectoryToDirectory(bin, distDir);
        Collection<File> scripts = listFiles(bin, new SuffixFileFilter(".sh"), TrueFileFilter.INSTANCE);
        for (File sc : scripts) {
            sc.setExecutable(true);
        }
        File conf = new File(project.getBasedir(), "src/main/conf");
        copyDirectoryToDirectory(conf, distDir);
        File webapp = new File(project.getBasedir(), "src/main/webapp");
        copyDirectoryToDirectory(webapp, distDir);
    }

    private void copyLauncher() throws IOException {
        File destDir = new File(distDir, "bin");
        String destName = project.getArtifact().getArtifactId();
        final File dest = new File(destDir, destName + ".jar");
        copyFile(project.getArtifact().getFile(), dest);
    }

    private void copyInstallerResources() throws IOException {
        File resources = new File(project.getBasedir(), "src/main/installer/resources");
        copyDirectoryToDirectory(resources, baseDir);
    }

    private void runIzPackCompiler() throws Exception {
        CompilerConfig compilerConfig = new CompilerConfig(installFile.getAbsolutePath(), baseDir.getAbsolutePath(), "standard", outputFile.getAbsolutePath());
        CompilerConfig.setIzpackHome(baseDir.getAbsolutePath());
        compilerConfig.executeCompiler();
    }
}
