export function checkIfNotNull(param) {
  const data = param !== '' && param !== null && param !== undefined ? param : '#';
  return data;
}
