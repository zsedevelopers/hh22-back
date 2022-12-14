package com.zse.hh22.wallet.domain.document;

import com.zse.hh22.user.domain.Image;
import com.zse.hh22.wallet.api.CreateIdentityCardDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name = "identity_cards")
@Entity
@Data
@NoArgsConstructor
public class IdentityCardEntity extends DocumentEntity {

    @NotBlank(message = "Pesel cannot be blank")
    private String pesel;

    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Sex cannot be blank")
    private Sex sex;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotBlank(message = "Family names cannot be blank")
    private String familyName;

    @NotBlank(message = "Mother name cannot be blank")
    private String motherName;

    @NotBlank(message = "Father name cannot be blank")
    private String fatherName;

    @Nullable
    @Pattern(regexp = "[\\d]{6}")
    private String CAN;

    public IdentityCardEntity(CreateIdentityCardDTO requestDTO) {
        this.documentStatus = DocumentStatus.UNVERIFIED;
        this.documentType = DocumentType.IDENTITY_CARD;
        this.picture = new Image(requestDTO.picture());
        this.frontOfDocumentImage = new Image(requestDTO.frontOfDocumentImage());
        this.backOfDocumentImage = new Image(requestDTO.backOfDocumentImage());
        this.firstName = requestDTO.firstName();
        this.secondName = requestDTO.secondName();
        this.surname = requestDTO.surname();
        this.nationality = requestDTO.nationality();
        this.documentNumber = requestDTO.documentNumber();
        this.expiryDate = requestDTO.expiryDate();
        this.birthDate = requestDTO.birthDate();
        this.sex = Sex.valueOf(requestDTO.sex().toUpperCase());
        this.CAN = requestDTO.CAN();
        this.placeOfBirth = requestDTO.placeOfBirth().toUpperCase();
        this.pesel = requestDTO.pesel();
        this.familyName = requestDTO.familyName();
        this.motherName = requestDTO.motherName();
        this.fatherName = requestDTO.fatherName();
        this.issuingAuthority = requestDTO.issuingAuthority();
        this.dateOfIssue = requestDTO.dateOfIssue();
    }
}
