package org.zerock.b01_2.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 여러개의 파일이 업로드 되면 업로드 결과도 여러 개 발생하게 되고 여러 정보를 반환해야 하므로 별도의 DTO를 구성해서 반환하도록 함
// 이미지 여부를 객체로 구성
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    private String uuid;

    private String fileName;

    private boolean img;

    public String getLink(){

        if(img){
            return "s_"+ uuid +"_"+fileName; //이미지인 경우 섬네일
        }else {
            return uuid+"_"+fileName;
        }
    }
}