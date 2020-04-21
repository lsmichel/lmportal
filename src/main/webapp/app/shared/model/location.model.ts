import { ISite } from 'app/shared/model/site.model';

export interface ILocation {
  id?: number;
  conntry?: string;
  city?: string;
  zipCode?: string;
  adress?: string;
  sites?: ISite[];
}

export const defaultValue: Readonly<ILocation> = {};
