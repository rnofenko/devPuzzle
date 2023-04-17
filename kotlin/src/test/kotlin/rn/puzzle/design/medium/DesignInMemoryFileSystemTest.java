package rn.puzzle.design.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Ass;

import java.util.*;


public class DesignInMemoryFileSystemTest {

    private static class FileSystem {
        private final Map<String, String> _fileContents = new HashMap<>();
        private final Map<String, List<String>> _structure = new HashMap<>();

        public FileSystem() {
            _structure.put("/", new ArrayList<>());
        }

        public List<String> ls(String path) {
            if (_fileContents.containsKey(path)) {
                return Collections.singletonList(_getDirOrFileName(path));
            }
            List<String> list = _structure.get(path);
            return list == null ? new ArrayList<>() : list;
        }

        public void mkdir(String path) {
            if (_isRoot(path) || _structure.containsKey(path)) {
                return;
            }
            String parent = _getParentDir(path);
            mkdir(parent);
            _structure.computeIfAbsent(path, k -> new ArrayList<>());
            _addFileOrDirIntoParent(path);
        }

        public void addContentToFile(String filePath, String content) {
            if (_fileContents.containsKey(filePath)) {
                _fileContents.compute(filePath, (key, value) -> value + content);
            } else {
                _fileContents.put(filePath, content);
                _addFileOrDirIntoParent(filePath);
            }
        }

        public String readContentFromFile(String filePath) {
            return _fileContents.get(filePath);
        }

        private void _addFileOrDirIntoParent(String filePath) {
            String parent = _getParentDir(filePath);
            String fileName = _getDirOrFileName(filePath);
            List<String> parentList = _structure.get(parent);
            parentList.add(fileName);
            parentList.sort(String::compareTo);
        }

        private String _getParentDir(String path) {
            int idx = path.lastIndexOf('/');
            if (idx == 0) {
                return "/";
            }
            return path.substring(0, idx);
        }

        private String _getDirOrFileName(String path) {
            int idx = path.lastIndexOf('/');
            return path.substring(idx + 1);
        }

        private boolean _isRoot(String path) {
            return path.equals("/");
        }
    }

    @Test
    public void test2() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/a");
        Assert.assertTrue(fileSystem.ls("/a").isEmpty());
        Ass.assertListEquals(Collections.singletonList("a"), fileSystem.ls("/"));
        fileSystem.mkdir("/z");
        Ass.assertListEquals(Arrays.asList("a", "z"), fileSystem.ls("/"));
        Ass.assertListEquals(Arrays.asList("a", "z"), fileSystem.ls("/"));
        fileSystem.addContentToFile("/a/c", "shetopcy");
        Assert.assertTrue(fileSystem.ls("/z").isEmpty());
        Ass.assertListEquals(Collections.singletonList("c"), fileSystem.ls("/a/c"));
        Ass.assertListEquals(Collections.singletonList("c"), fileSystem.ls("/a"));
    }

    @Test
    public void test1() {
        FileSystem fileSystem = new FileSystem();
        Assert.assertTrue(fileSystem.ls("/").isEmpty());
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        Ass.assertListEquals(Collections.singletonList("a"), fileSystem.ls("/"));
        Assert.assertEquals("hello", fileSystem.readContentFromFile("/a/b/c/d"));
    }
}
