package br.com.zup.desafioorangetalents.exceptionshandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL) // anotacao para nao mostrar campos null
public @Data class ExceptionMessage {

    private int status;
    private OffsetDateTime timestamp;
    private String title;
    private List<Field> fields;

    public ExceptionMessage(int status, OffsetDateTime timestamp, String title, List<Field> fields) {
        this.status = status;
        this.timestamp = timestamp;
        this.title = title;
        this.fields = fields;
    }
}

@Data class Field {

    private String name;
    private String message;

    public Field(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
