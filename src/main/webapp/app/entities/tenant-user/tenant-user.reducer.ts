import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITenantUser, defaultValue } from 'app/shared/model/tenant-user.model';

export const ACTION_TYPES = {
  FETCH_TENANTUSER_LIST: 'tenantUser/FETCH_TENANTUSER_LIST',
  FETCH_TENANTUSER: 'tenantUser/FETCH_TENANTUSER',
  CREATE_TENANTUSER: 'tenantUser/CREATE_TENANTUSER',
  UPDATE_TENANTUSER: 'tenantUser/UPDATE_TENANTUSER',
  DELETE_TENANTUSER: 'tenantUser/DELETE_TENANTUSER',
  RESET: 'tenantUser/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITenantUser>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TenantUserState = Readonly<typeof initialState>;

// Reducer

export default (state: TenantUserState = initialState, action): TenantUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TENANTUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TENANTUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TENANTUSER):
    case REQUEST(ACTION_TYPES.UPDATE_TENANTUSER):
    case REQUEST(ACTION_TYPES.DELETE_TENANTUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TENANTUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TENANTUSER):
    case FAILURE(ACTION_TYPES.CREATE_TENANTUSER):
    case FAILURE(ACTION_TYPES.UPDATE_TENANTUSER):
    case FAILURE(ACTION_TYPES.DELETE_TENANTUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANTUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_TENANTUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TENANTUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_TENANTUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TENANTUSER):
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

const apiUrl = 'api/tenant-users';

// Actions

export const getEntities: ICrudGetAllAction<ITenantUser> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TENANTUSER_LIST,
    payload: axios.get<ITenantUser>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITenantUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TENANTUSER,
    payload: axios.get<ITenantUser>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITenantUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TENANTUSER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITenantUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TENANTUSER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITenantUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TENANTUSER,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
