export interface ISite {
  id?: number;
  libelle?: string;
  description?: string;
  tenantId?: number;
  locationId?: number;
}

export const defaultValue: Readonly<ISite> = {};
