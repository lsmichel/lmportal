import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITenantSuperAdmin, defaultValue } from 'app/shared/model/tenant-super-admin.model';

export const ACTION_TYPES = {
  FETCH_TENANTSUPERADMIN_LIST: 'tenantSuperAdmin/FETCH_TENANTSUPERADMIN_LIST',
  FETCH_TENANTSUPERADMIN: 'tenantSuperAdmin/FETCH_TENANTSUPERADMIN',
  CREATE_TENANTSUPERADMIN: 'tenantSuperAdmin/CREATE_TENANTSUPERADMIN',
  UPDATE_TENANTSUPERADMIN: 'tenantSuperAdmin/UPDATE_TENANTSUPERADMIN',
  DELETE_TENANTSUPERADMIN: 'tenantSuperAdmin/DELETE_TENANTSUPERADMIN',
  RESET: 'tenantSuperAdmin/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITenantSuperAdmin>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TenantSuperAdminState = Readonly<typeof initialState>;

// Reducer

export default (state: TenantSuperAdminState = initialState, action): TenantSuperAdminState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TENANTSUPERADMIN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TENANTSUPERADMIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TENANTSUPERADMIN):
    case REQUEST(ACTION_TYPES.UPDATE_TENANTSUPERADMIN):
    case REQUEST(ACTION_TYPES.DELETE_TENANTSUPERADMIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TENANTSUPERADMIN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TENANTSUPERADMIN):
    case FAILURE(ACTION_TYPES.CREATE_TENANTSUPERADMIN):
    case FAILURE(ACTION_TYPES.UPDATE_TENANTSUPERADMIN):
    case FAILURE(ACTION_TYPES.DELETE_TENANTSUPERADMIN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANTSUPERADMIN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANTSUPERADMIN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TENANTSUPERADMIN):
    case SUCCESS(ACTION_TYPES.UPDATE_TENANTSUPERADMIN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TENANTSUPERADMIN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/tenant-super-admins';

// Actions

export const getEntities: ICrudGetAllAction<ITenantSuperAdmin> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TENANTSUPERADMIN_LIST,
    payload: axios.get<ITenantSuperAdmin>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITenantSuperAdmin> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TENANTSUPERADMIN,
    payload: axios.get<ITenantSuperAdmin>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITenantSuperAdmin> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TENANTSUPERADMIN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITenantSuperAdmin> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TENANTSUPERADMIN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITenantSuperAdmin> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TENANTSUPERADMIN,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
