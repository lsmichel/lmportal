import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITenantWithAdmin, defaultValue } from 'app/shared/CustomModel/tenant-with-admin.model';

export const ACTION_TYPES = {
  FETCH_TENANWITHADMIN_LIST: 'tenantwithadmin/FETCH_TENANWITHADMIN_LIST',
  FETCH_TENANWITHADMIN: 'tenantwithadmin/FETCH_TENANWITHADMIN',
  CREATE_TENANWITHADMIN: 'tenantwithadmin/CREATE_TENANWITHADMIN',
  UPDATE_TENANWITHADMIN: 'tenantwithadmin/UPDATE_TENANWITHADMIN',
  DELETE_TENANWITHADMIN: 'tenantwithadmin/DELETE_TENANWITHADMIN',
  RESET: 'tenantwithadmin/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITenantWithAdmin>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TenantWithAdminState = Readonly<typeof initialState>;

// Reducer

export default (state: TenantWithAdminState = initialState, action): TenantWithAdminState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TENANWITHADMIN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TENANWITHADMIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TENANWITHADMIN):
    case REQUEST(ACTION_TYPES.UPDATE_TENANWITHADMIN):
    case REQUEST(ACTION_TYPES.DELETE_TENANWITHADMIN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TENANWITHADMIN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TENANWITHADMIN):
    case FAILURE(ACTION_TYPES.CREATE_TENANWITHADMIN):
    case FAILURE(ACTION_TYPES.UPDATE_TENANWITHADMIN):
    case FAILURE(ACTION_TYPES.DELETE_TENANWITHADMIN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANWITHADMIN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANWITHADMIN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TENANWITHADMIN):
    case SUCCESS(ACTION_TYPES.UPDATE_TENANWITHADMIN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TENANWITHADMIN):
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

const apiUrl = 'api/tenants-with-admin';

// Actions

export const getEntities: ICrudGetAllAction<ITenantWithAdmin> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TENANWITHADMIN_LIST,
    payload: axios.get<ITenantWithAdmin>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITenantWithAdmin> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TENANWITHADMIN,
    payload: axios.get<ITenantWithAdmin>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITenantWithAdmin> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TENANWITHADMIN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITenantWithAdmin> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TENANWITHADMIN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITenantWithAdmin> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TENANWITHADMIN,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
