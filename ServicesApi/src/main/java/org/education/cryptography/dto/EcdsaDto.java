package org.education.cryptography.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EcdsaDto {

    private byte[] message;
    private byte[] subscribeMessage;
    private byte[] publicKey;

}