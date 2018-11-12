export interface UserResponse {
  userId?: string;
  name: string;
  mail: string;
  age?: number;
  gender?: GenderEnum;
}

export enum GenderEnum {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  UNKNOWN = 'UNKNOWN'
}
