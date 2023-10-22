package lk.ijse.travelservice.util.mappers;

import lk.ijse.travelservice.dto.TravelPackageDto;
import lk.ijse.travelservice.dto.embedded.*;
import lk.ijse.travelservice.entity.TravelPackage;
import lk.ijse.travelservice.entity.embedded.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = PackageMapper.class)
public interface TravelMapper {
    TravelMapper travelMapper = Mappers.getMapper(TravelMapper.class);

    List<TravelPackageDto> toDto(List<TravelPackage> entity);

    List<TravelPackage> toEntity(List<TravelPackageDto> dto);

    TravelPackageDto toDto(TravelPackage entity);

    TravelPackage toEntity(TravelPackageDto dto);

    TravelDurationDto toDto(TravelDuration entity);

    TravelDuration toEntity(TravelDurationDto dto);

    VehicleDetailsDto toDto(VehicleDetails entity);

    VehicleDetails toEntity(VehicleDetailsDto dto);

    GuideDetailsDto toDto(GuideDetails entity);

    GuideDetails toEntity(GuideDetailsDto dto);

    HotelDetailsDto toDto(HotelDetails entity);

    HotelDetails toEntity(HotelDetailsDto dto);

    MemberCountDto toDto(MemberCount entity);

    MemberCount toEntity(MemberCountDto dto);

    PackageValueDetailsDto toDto(PackageValueDetails entity);

    PackageValueDetails toEntity(PackageValueDetailsDto dto);

    PaymentDetailsDto toDto(PaymentDetails entity);
    PaymentDetails toEntity(PaymentDetailsDto dto);

}
