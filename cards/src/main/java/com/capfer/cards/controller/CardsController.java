package com.capfer.cards.controller;

import com.capfer.cards.constants.CardConstants;
import com.capfer.cards.dto.CardDTO;
import com.capfer.cards.dto.CardsContactInfoDTO;
import com.capfer.cards.dto.ErrorResponseDTO;
import com.capfer.cards.dto.ResponseDTO;
import com.capfer.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards in CapferBank",
        description = "CRUD REST APIs in CapferBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class CardsController {

    private final ICardService cardService;
    private final Environment environment;
    private final CardsContactInfoDTO cardsContactInfoDTO;

    @Value(value = "${build.version}")
    private String version;

    public CardsController(ICardService cardService, Environment environment, CardsContactInfoDTO cardsContactInfoDTO) {
        this.cardService = cardService;
        this.environment = environment;
        this.cardsContactInfoDTO = cardsContactInfoDTO;
    }

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside CapferBank"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "HTTP Status CREATED"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(
            @Valid
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardConstants.STATUS_CREATED_201, CardConstants.CREATED_MESSAGE));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardDTO> fetchCardDetails(
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            @RequestParam String mobileNumber

    ){
        CardDTO cardDTO = cardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDTO);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "Expectation Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) {
        final boolean isUpdated = cardService.updateCard(cardDTO);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.UPDATE_STATUS_417, CardConstants.UPDATE_MESSAGE_417));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(CardConstants.STATUS_OK_200, CardConstants.MESSAGE_OK));
    }


    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.DELETE_MESSAGE_417));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(CardConstants.STATUS_OK_200, CardConstants.MESSAGE_OK));
    }

    @Operation(
            summary = "Get Build Information",
            description = "Get Build Information that is deployed into loans microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(version);
    }

    @Operation(
            summary = "Get Java Version Information",
            description = "Get Java Version Information that is deployed into cards microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
    }

    @Operation(
            summary = "Get Contact Information",
            description = "The Contact Information that can be used to reach out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDTO);
    }

}
