import * as React from 'react';

const Button: React.FC<React.ButtonHTMLAttributes<HTMLButtonElement>> = ({
  children,
  ...props
}) => (
  <button type='button' {...props}>
    {children}
  </button>
);

export { Button };
