export interface VehicleResponse {
    vehicleId: string;
    currentPosition: string;
    design: InteriorDesignType;
    model: VehicleModelType;
    engine: EngineType;
    infotainment: InfotainmentType;
}

export enum VehicleModelType {
    SEDAN="SEDAN",
    HATCHBACK="HATCHBACK",
    SUV="SUV",
    VAN="VAN",
    CONVERTIBLE="CONVERTIBLE"
}

export enum EngineType {
    DIESEL="DIESEL",
    GASOLINE="GASOLINE",
    HYBRID="HYBRID",
    ELECTRIC="ELECTRIC"
}

export enum InfotainmentType {
    NAVIGATION="NAVIGATION",
    RADIO="RADIO",
    ENTERTAINMENT="ENTERTAINMEN"
}

export enum InteriorDesignType {
    SPORT="SPORT",
    STANDARD="STANDARD",
    LEATHER="LEATHER"
}