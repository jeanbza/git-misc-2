import ReactDOM from 'react';
import React from 'react';
import TestUtils from 'react-addons-test-utils';
import sinon from 'sinon';

var ActionLink = React.createClass({
  render: function() {
    return <a href="some url" onClick={this.props.onClick}>Some text</a>;
  }
});

describe("reactDOM and react-addons-test-utils", function() {
  it("should invoke the supplied function when clicked", function() {
    const onClick = sinon.spy();
    const actionLink = <ActionLink onClick={onClick}/>;

    const div = document.createElement('div');
    ReactDOM.render(actionLink, div);
    const element = div.children[0];

    TestUtils.Simulate.click(element);
    expect(onClick.called).toEqual(true);
  });
});
