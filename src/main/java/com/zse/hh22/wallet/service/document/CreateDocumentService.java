package com.zse.hh22.wallet.service.document;

import java.security.Principal;

import com.zse.hh22.wallet.api.CreateDriverLicenceDTO;
import com.zse.hh22.wallet.api.CreateIdentityCardDTO;
import com.zse.hh22.wallet.api.CreatePassportDTO;

public interface CreateDocumentService {
    void createIdentityCard(CreateIdentityCardDTO requestDTO, Principal loggedUser);

    void createPassport(CreatePassportDTO requestDTO, Principal loggedUser);

    void createDriverLicence(CreateDriverLicenceDTO requestDTO, Principal loggedUser);
}