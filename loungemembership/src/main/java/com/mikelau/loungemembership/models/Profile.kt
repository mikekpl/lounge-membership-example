package com.mikelau.loungemembership.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile(
    @Expose
    @SerializedName("AccountGuid")
    val accountGuid: String? = null,

    @Expose
    @SerializedName("UserId")
    val userId: String? = null,

    @Expose
    @SerializedName("BillingAddress")
    val billingAddress: BillingAddress? = null,

    @Expose
    @SerializedName("BillingAddresses")
    val billingAddresses: List<BillingAddress>? = null,

    @Expose
    @SerializedName("Base64MemberBarcode")
    val membershipCardBarcode: String? = null,

    @Expose
    @SerializedName("MemberBarcodeText")
    val membershipCardBarcodeText: String? = null,

    @Expose
    @SerializedName("CreditCards")
    val creditCards: List<CreditCard> = emptyList(),

    @Expose
    @SerializedName("EmailAddress")
    val emailAddress: String? = null,

    @Expose
    @SerializedName("EmailAddresses")
    val emailAddresses: List<EmailAddress>? = null,

    @Expose
    @SerializedName("EnrollmentDate")
    val enrollmentDate: String? = null,

    @Expose
    @SerializedName("BoardRoomExpirationDate")
    val boardRoomExpirationDate: String? = null,

    @Expose
    @SerializedName("TierExpirationDate")
    val tierStatusExpirationDate: String? = null,

    @Expose
    @SerializedName("FirstName")
    val firstName: String? = null,

    @Expose
    @SerializedName("FullName")
    val fullName: String? = null,

    @Expose
    @SerializedName("IsMillionMiler")
    val isMillionMiler: Boolean = false,

    @Expose
    @SerializedName("IsClub49")
    val isClub49: Boolean = false,

    @Expose
    @SerializedName("IsBoardroomMember")
    val isBoardRoomMember: Boolean = false,

    @Expose
    @SerializedName("IsLoungePlusMember")
    val isLoungePlusMember: Boolean = false,

    @Expose
    @SerializedName("IsAlaskaVisaHolder")
    val isAlaskaVisaHolder: Boolean = false,

    @Expose
    @SerializedName("MileagePlanNumber")
    val mileagePlanNumber: String? = null,

    @Expose
    @SerializedName("MilesAvailable")
    val milesAvailable: Int = 0,

    @Expose
    @SerializedName("MillionMilesFlown")
    val millionMilesFlown: Int = 0,

    @Expose
    @SerializedName("MillionMilesQualLevel")
    val millionMilesQualLevel: Int = 0,

    @Expose
    @SerializedName("NextTier")
    val nextTier: String? = null,

    @Expose
    @SerializedName("NickName")
    val nickName: String? = null,

    @Expose
    @SerializedName("Phones")
    val phones: List<PhoneNumber> = emptyList(),

    @Expose
    @SerializedName("QualLevels")
    val qualLevels: QualLevels? = null,

    @Expose
    @SerializedName("Tier")
    val tier: String? = null,

    @Expose
    @SerializedName("TierStatus")
    val tierStatus: String? = null,

    @Expose
    @SerializedName("OneWorldTierStatus")
    val oneWorldTierStatus: String? = null,

    @Expose
    @SerializedName("YTDFlown")
    val ytdFlown: QualLevels? = null,

    @Expose
    @SerializedName("PrimaryCreditCard")
    val primaryCreditCard: CreditCard? = null,

    @Expose
    @SerializedName("PrimaryBillingAddress")
    val primaryBillingAddress: BillingAddress? = null,

    @Expose
    @SerializedName("WalletBalance")
    val walletBalance: String? = null,

    @Expose
    @SerializedName("AlaskaSegments")
    val alaskaSegmentsRequired: Int = 0
) {
    data class BillingAddress(
        @Expose
        @SerializedName("AddressLine1")
        val addressLine1: String? = null,

        @Expose
        @SerializedName("AddressLine2")
        val addressLine2: String? = null,

        @Expose
        @SerializedName("City")
        val city: String? = null,

        @Expose
        @SerializedName("State")
        val state: String? = null,

        @Expose
        @SerializedName("PostalCode")
        val postalCode: String? = null,

        @Expose
        @SerializedName("Country")
        val country: String? = null
    )

    data class CreditCard(
        @Expose
        @SerializedName("CardNumber")
        val cardNumber: String? = null,

        @Expose
        @SerializedName("ExpirationDate")
        val expirationDate: String? = null,

        @Expose
        @SerializedName("IsDefault")
        val isDefault: Boolean = false
    )

    data class EmailAddress(
        @Expose
        @SerializedName("Email")
        val email: String? = null
    )

    data class PhoneNumber(
        @Expose
        @SerializedName("PhoneNumber")
        val phoneNumber: String? = null
    )

    data class QualLevels(
        @Expose
        @SerializedName("Level")
        val level: String? = null
    )
}