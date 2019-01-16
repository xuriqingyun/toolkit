package zhouxu.site.statistics;

import java.io.File;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description:文件类型枚举
 * User: zhouxu
 * Date: 2019-01-16 9:42
 */
public enum  FileType {
    UNKOWN("unkown"),
    TEXT("txt"),
    CONFIG("config"),
    DOC("doc"),
    JAVA("java"),
    H("h"),
    C("c"),
    XLS("xls")
    ;
    private String code;

    FileType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * @Author zhouxu
     * @Description //类型获取FileType
     * @Date 2019/1/16 11:12
     * @Param [code]
     * @return zhouxu.site.multithreading.statistics.FileType
     * @throws
     **/
    public static FileType getType(String code){
        FileType[] values = FileType.values();
        for(FileType fileType:FileType.values()){
            if(fileType.getCode().equals(code)){
                return fileType;
            }
        }
        System.out.println(FileType.class+": unkown type ["+code+"]");
        return FileType.UNKOWN;
    }

    /**
     * @Author zhouxu
     * @Description //类型获取FileType
     * @Date 2019/1/16 11:12
     * @Param [code]
     * @return zhouxu.site.multithreading.statistics.FileType
     * @throws
     **/
    public static FileType getType(File file){
        Objects.nonNull(file);
        FileType[] values = FileType.values();
        for(FileType fileType:FileType.values()){
            if(fileType.getCode().equals(Istatistics.fileType(file))){
                return fileType;
            }
        }
        System.out.println(FileType.class+": unkown file type ["+ file.getAbsolutePath()+"]");
        return FileType.UNKOWN;
    }
}
