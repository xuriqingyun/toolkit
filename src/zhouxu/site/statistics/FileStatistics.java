package zhouxu.site.statistics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:文件统计
 * User: zhouxu
 * Date: 2019-01-16 10:05
 */
public class FileStatistics implements Istatistics {

//    private final String path;

    private final File file;

    public FileStatistics(String path) {
        this(new File(path));
    }

    public FileStatistics(File file){
        this.file = file;
        Istatistics.exits(file);
    }

    @Override
    public int fileSize() {
        return Istatistics.files(this.file,-1).size();
    }

    @Override
    public int directorySize() {
        return Istatistics.directorys(this.file,-1).size();
    }

    @Override
    public double bytes() {
        List<File> files = Istatistics.files(this.file, -1);
        double bytes=0;
        for(File file: files){
            bytes+=file.length();
        }
        return bytes;
    }

    @Override
    public List<File> files() {
        return Istatistics.files(this.file,-1);
    }

    @Override
    public List<File> directorys() {
        return Istatistics.directorys(this.file,-1);
    }

    @Override
    public Map<FileType, Integer> countTpye() {
        Map<FileType,Integer> countTpye = new HashMap<>();
        List<File> files = Istatistics.files(this.file, -1);
        for(File file:files){
            FileType type = FileType.getType(file);
            if(countTpye.containsKey(type)){
                countTpye.put(type,countTpye.get(type)+1);
            }else{
                countTpye.put(type,1);
            }
        }
        return countTpye;
    }

    @Override
    public Map<FileType, List<File>> countFiles() {
        Map<FileType,List<File>> countTpye = new HashMap<>();
        List<File> files = Istatistics.files(this.file, -1);
        for(File file:files){
            FileType type = FileType.getType(file);
            if(countTpye.containsKey(type)){
                countTpye.get(type).add(file);
            }else{
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                countTpye.put(type,fileList);
            }
        }
        return countTpye;
    }
}
