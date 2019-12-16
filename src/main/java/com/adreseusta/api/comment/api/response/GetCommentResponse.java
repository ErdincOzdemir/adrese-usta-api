package com.adreseusta.api.comment.api.response;

import com.adreseusta.api.common.api.response.BaseResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCommentResponse extends BaseResponse {

    @ApiModelProperty(name = "fullName", notes = "full name of commentator")
    private String fullName;

    @ApiModelProperty(name = "title", notes = "title of commentator")
    private String title;

    @ApiModelProperty(name = "comment", notes = "comment")
    private String comment;

    @ApiModelProperty(name = "image", notes = "image of commentator")
    private byte[] image;

}
