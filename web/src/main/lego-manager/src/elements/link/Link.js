import React, { Component } from 'react';
import { Link as NativeLink, browserHistory} from 'react-router'

class Link extends Component {

  static redirect(url) {
    browserHistory.push(this.removeTrailingSlash(process.env.PUBLIC_URL) + url)
  }

  static removeTrailingSlash(url) {
    return url.replace(/\/+$/, "")
  }

  render() {
    let underline = !this.props.underline ? {
      style: { textDecoration: 'none' }
    } : {};
    // custom link that automatically adds prefix to url
    return <NativeLink
      {...this.props}
      {...underline}
      to={Link.removeTrailingSlash(process.env.PUBLIC_URL) + this.props.to} />
  }
}

export default Link
