export const REQUEST_CHOOSE_USERNAME = 'REQUEST_CHOOSE_USERNAME';
export const CHOOSE_USERNAME_SUCCESS = 'CHOOSE_USERNAME_SUCCESS';
export const CHOOSE_USERNAME_FAILURE = 'CHOOSE_USERNAME_FAILURE';

export const requestChooseUsername = (username) => ({
    type: REQUEST_CHOOSE_USERNAME,
    payload: { username }
});

export const chooseUsernameSuccess = (username) => ({
    type: CHOOSE_USERNAME_SUCCESS,
    payload: { username }
});

export const chooseUsernameFailure = (error) => ({
    type: CHOOSE_USERNAME_FAILURE,
    payload: { error }
});

