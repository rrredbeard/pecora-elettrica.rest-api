package com.pecora_elettrica.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecora_elettrica.api.model.EBookCopy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO implements Serializable {

    /** static **/

    private static final long serialVersionUID = -3466423045433667291L;

    /** instance **/

    @NonNull
    private String isbn;

    private String title;
    private String author;

    @JsonProperty("pages_no")
    private Long pagesNumber;

    @JsonProperty("publication_date")
    private Date publication;

    @JsonProperty("previous_edition")
    private String previous;

    private Integer replies;

    @JsonIgnore
    private Set<EBookCopy> bookCopies;

    /** composite **/

    public Integer getReplies() {
        if (replies == null && bookCopies != null)
            setReplies(bookCopies.size());

        return replies;
    }

}