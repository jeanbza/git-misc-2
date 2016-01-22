require_relative '../spec_helper'

describe 'the app' do
  it 'does something' do
    get '/some_endpoint'
    expect(last_response).to be_ok
  end
end
