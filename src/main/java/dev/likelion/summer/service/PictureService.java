package dev.likelion.summer.service;

import dev.likelion.summer.dto.PictureDto;
import dev.likelion.summer.entity.Picture;
import dev.likelion.summer.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@Service
@AllArgsConstructor
@Getter
@Setter
public class PictureService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PictureRepository pictureRepository;

    /**
     * 파일 업로드
     */

//    public Picture uploadFile(MultipartFile multiRequest) throws Exception {
//
////        // 파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져온다.
////        Map<String, MultipartFile> files = multiRequest.getFileMap();
////
////        // files.entrySet()의 요소를 읽어온다.
////        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
//
//        Picture picture = null;
//
//        MultipartFile mFile;
//
//        // 파일이 업로드 될 경로를 지정한다.
//        // 아래의 경우 apple 사용자의 Downloads에 파일이 업로드된다.
//        String filePath = "C:\\Users\\apple\\Downloads\\";
//
//        // 파일명이 중복되었을 경우, 사용할 스트링 객체
//        String saveFileName = "", savaFilePath = "";
//
//        // 읽어 올 요소가 있으면 true, 없으면 false를 반환한다.
//        while (itr.hasNext()) {
//
//            Entry < String, MultipartFile > entry = itr.next();
//
//            // entry에 값을 가져온다.
//            mFile = entry.getValue();
//
//            // 파일명
//            String fileName = mFile.getOriginalFilename();
//
//            // 확장자를 제외한 파일명
//            String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
//
//            // 확장자
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//            // 저장될 경로와 파일명
//            String saveFilePath = filePath + File.separator + fileName;
//
//            // filePath에 해당되는 파일의 File 객체를 생성한다.
//            File fileFolder = new File(filePath);
//
//            if (!fileFolder.exists()) {
//                // 부모 폴더까지 포함하여 경로에 폴더를 만든다.
//                if (fileFolder.mkdirs()) {
//                    logger.info("[file.mkdirs] : Success");
//                } else {
//                    logger.error("[file.mkdirs] : Fail");
//                }
//            }
//
//            File saveFile = new File(saveFilePath);
//
//            // saveFile이 File이면 true, 아니면 false
//            // 파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
//
//            if (saveFile.isFile()) {
//                boolean _exist = true;
//                int index = 0;
//
//            // 동일한 파일명이 존재하지 않을때까지 반복한다.
//                while (_exist) {
//                    index++;
//
//                    saveFileName = fileCutName + "(" + index + ")." + fileExt;
//
//                    String dictFile = filePath + File.separator + saveFileName;
//
//                    _exist = new File(dictFile).isFile();
//
//                    if (!_exist) {
//                        savaFilePath = dictFile;
//                    }
//                }
//                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.
//
//                mFile.transferTo(new File(savaFilePath));
//            } else {
//
//                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.
//                mFile.transferTo(saveFile);
//            }
//
//            String finalFileName;
//            if(saveFileName.equals("")) {
//                finalFileName = fileName;
//            }
//            else {
//                finalFileName = saveFileName;
//            }
//
//            picture = Picture.toPicture(filePath, finalFileName);
//        }
//
//        pictureRepository.save(picture);
//
//        return picture;
//    }

    public PictureDto uploadFile(MultipartFile file) throws Exception {


        // 파일이 업로드 될 경로를 지정한다.
        // 아래의 경우 사용자의 Downloads에 파일이 업로드된다.
        String filePath = "/Users/kimdong-gyu/Desktop/LikeLion/picture";

        // 파일명이 중복되었을 경우, 사용할 스트링 객체
        String saveFileName = "", saveFilePath = "";

        // 파일명
        String fileName = file.getOriginalFilename();

        // 확장자를 제외한 파일명
        String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));

        // 확장자
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 저장될 경로와 파일명
        String saveFileDir = filePath + File.separator + fileName;

        // filePath에 해당되는 파일의 File 객체를 생성한다.
        File fileFolder = new File(filePath);

        if (!fileFolder.exists()) {
            // 부모 폴더까지 포함하여 경로에 폴더를 만든다.
            if (fileFolder.mkdirs()) {
                logger.info("[file.mkdirs] : Success");
            } else {
                logger.error("[file.mkdirs] : Fail");
            }
        }

        File saveFile = new File(saveFileDir);

        // saveFile이 File이면 true, 아니면 false
        // 파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
        if (saveFile.isFile()) {
            boolean _exist = true;
            int index = 0;

            // 동일한 파일명이 존재하지 않을때까지 반복한다.
            while (_exist) {
                index++;

                saveFileName = fileCutName + "(" + index + ")." + fileExt;

                String dictFile = filePath + File.separator + saveFileName;

                _exist = new File(dictFile).isFile();

                if (!_exist) {
                    saveFilePath = dictFile;
                }
            }
            //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.

            file.transferTo(new File(saveFilePath));
        } else {

            // 생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.
            file.transferTo(saveFile);
        }

        String finalFileName;
        if (saveFileName.equals("")) {
            finalFileName = fileName;
        } else {
            finalFileName = saveFileName;
        }


        PictureDto pictureDto = PictureDto.toPictureDto(filePath, finalFileName);

//        pictureRepository.save(picture);

        return pictureDto;
    }


    @Transactional
    public Long savePicture(Picture picture) {
        return pictureRepository.save(picture).getPictureId();
    }

    @Transactional
    public Picture getOnePicture(Long id) {
        return pictureRepository.getOne(id);
    }

}
