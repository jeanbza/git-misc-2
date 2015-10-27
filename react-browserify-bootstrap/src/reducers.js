import { combineReducers } from 'redux';

export default function foo(state = {}, action) {
  switch (action.type) {
  default:
    return state
  }
}

const rootReducer = combineReducers({
  foo
});

export default rootReducer;
