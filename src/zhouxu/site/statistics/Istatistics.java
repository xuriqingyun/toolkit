package zhouxu.site.statistics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description:统计接口
 * User: zhouxu
 * Date: 2019-01-16 9:36
 */
public interface Istatistics {
    /**
     * @Author zhouxu
     * @Description //统计数量
     * @Date 2019/1/16 9:38
     * @Param []
     * @return int
     * @throws
     **/
    int fileSize();

    /**
     * @Author zhouxu
     * @Description //文件夹数量
     * @Date 2019/1/16 11:34
     * @Param []
     * @return int
     * @throws
     **/
    int directorySize();

    /**
     * @Author zhouxu
     * @Description //统计字节数
     * @Date 2019/1/16 9:38
     * @Param []
     * @return double
     * @throws
     **/
    double bytes();

    /**
     * @Author zhouxu
     * @Description //统计文件
     * @Date 2019/1/16 9:38
     * @Param []
     * @return java.util.List<java.io.File>
     * @throws
     **/
    List<File> files();

    /**
     * @Author zhouxu
     * @Description //文件夹数量
     * @Date 2019/1/16 11:35
     * @Param []
     * @return java.util.List<java.io.File>
     * @throws
     **/
    List<File> directorys();

    /**
     * @Author zhouxu
     * @Description //每类文件数量
     * @Date 2019/1/16 9:48
     * @Param []
     * @return Map<FileType,int>
     * @throws
     **/
    Map<FileType,Integer> countTpye();

    /**
     * @Author zhouxu
     * @Description //每类设备集合
     * @Date 2019/1/16 9:50
     * @Param []
     * @return java.util.Map<zhouxu.site.multithreading.statistics.FileType,java.lang.Integer>
     * @throws
     **/
    Map<FileType,List<File>> countFiles();

    /**
     * @Author zhouxu
     * @Description //判断是否为文件
     * @Date 2019/1/16 9:39
     * @Param []
     * @return boolean
     * @throws
     **/
    static boolean isFile(File file){
        if(exits(file)&&file.isFile()){
            return true;
        }
        return false;
    }

    /**
     * @Author zhouxu
     * @Description //判断是否存在，不存在的情况以异常告知
     * @Date 2019/1/16 10:03
     * @Param [file]
     * @return boolean
     * @throws
     **/
    static boolean exits(File file){
        Objects.nonNull(file);
        if(!file.exists()){
            throw new BizException(String.format("%s not exits",file.getAbsoluteFile()));
        }
        return true;
    }
    /**
     * @Author zhouxu
     * @Description //合并集合
     * @Date 2019/1/16 10:41
     * @Param [target, source]
     * @return void
     * @throws
     **/
    static  <T> void combinList(List<T> target, List<T> source){
        Objects.requireNonNull(target);
        Objects.requireNonNull(source);
        for(T t : source){
            target.add(t);
        }
    }

    /**
     * @Author zhouxu
     * @Description //是否是文件夹
     * @Date 2019/1/16 9:41
     * @Param [file]
     * @return boolean
     * @throws
     **/
    static boolean isDirectory(File file){
        if(exits(file)&&file.isDirectory()){
            return true;
        }
        return false;
    }

    /**
     * @Author zhouxu
     * @Description //统计文件数量，layer为向下层数
     * @Date 2019/1/16 9:51
     * @Param [file, layer]
     * layer:>0表示向下层数，<0表示到底
     * @return int
     * @throws
     **/
//    static int files(File file, int layer){
//        int length=0;
//        if(layer>0){
//            //files layer
//            if(isFile(file)){
//                return 1;
//            }else if(isDirectory(file)){
//                for(File file1: file.listFiles()){
//                    if(isFile(file1)){
//                        length++;
//                    }else if(isDirectory(file1)){
//                        length+=files(file1,layer-1);
//                    }
//                }
//            }
//        }else if (layer==0){
//            //zero layer
//            if(isFile(file)){
//                return 1;
//            }
//        } else{
//            //no files
//            if(isDirectory(file)){
//
//                for(File file1 : file.listFiles()){
//                    if(isFile(file1)){
//                        length++;
//                    }else if(isDirectory(file1)){
//                        length+=files(file1,layer);
//                    }
//                }
//
//            }else if(isFile(file)){
//                return 1;
//            }
//        }
//        return length;
//    }
    static List<File> files(File file, int layer){
        List<File> files = new ArrayList<File>();
        if(layer>0){
            //files layer
            if(isFile(file)){
                files.add(file);
                return files;
            }else if(isDirectory(file)){
                for(File file1: file.listFiles()){
                    if(isFile(file1)){
                        files.add(file1);
                    }else if(isDirectory(file1)){
                        combinList(files, files(file1,layer-1));
                    }
                }
            }
        }else if (layer==0){
            //zero layer
            if(isFile(file)){
                files.add(file);
                return files;
            }
        } else{
            //no files
            if(isDirectory(file)){
                for(File file1 : file.listFiles()){
                    if(isFile(file1)){
                        files.add(file1);
                    }else if(isDirectory(file1)){
                        combinList(files, files(file1,layer));
                    }
                }

            }else if(isFile(file)){
                files.add(file);
                return files;
            }
        }
        return files;
    }

    /**
     * @Author zhouxu
     * @Description //文件夹数量
     * @Date 2019/1/16 11:39
     * @Param [file, layer]
     * @return java.util.List<java.io.File>
     * @throws
     **/
    static List<File> directorys(File file, int layer){
        List<File> files = new ArrayList<File>();
        if(layer>0){
            //files layer
            if(isDirectory(file)){
                for(File file1 : file.listFiles()){
                    if(isDirectory(file1)){
                        files.add(file1);
                        combinList(files, directorys(file1,layer-1));
                    }
                }
            }
        }else if (layer==0){
            //zero layer
            return files;
        } else{
            //no files
            if(isDirectory(file)){
                for(File file1 : file.listFiles()){
                    if(isDirectory(file1)){
                        files.add(file1);
                        combinList(files, directorys(file1,layer));
                    }
                }
            }
        }
        return files;
    }

    /**
     * @Author zhouxu
     * @Description //文件类别
     * @Date 2019/1/16 10:59
     * @Param []
     * @return java.lang.String
     * @throws
     **/
    static String fileType(File file){
        Objects.nonNull(file);
        String name = file.getName();
        if(name.lastIndexOf(".")!=-1){
            return name.substring(name.lastIndexOf(".")+1);
        }
        return "";
    }
}
