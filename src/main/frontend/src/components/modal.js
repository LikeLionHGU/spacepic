import React, { useState } from "react";
import styled from "styled-components";
import close from "../img/close.png";

const Container = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 100;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Background = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(5px);
  animation: modal-bg-show 1s;
  @keyframes modal-bg-show {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
`;

const ModalBlock = styled.div`
  position: absolute;
  top: 6.5rem;
  border-radius: 10px;
  padding: 1.5rem;
  background-color: black;
  width: 60rem;
  @media (max-width: 1120px) {
    width: 50rem;
  }
  @media (max-width: 50rem) {
    width: 80%;
  }
  min-height: 35rem;
  animation: modal-show 1s;
  @keyframes modal-show {
    from {
      opacity: 0;
      margin-top: -50px;
    }
    to {
      opacity: 1;
      margin-top: 0;
    }
  }
`;

const Close = styled.img`
  float: right;
  width: 20px;
  height: 20px;
`;

const Contents = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Mtitle = styled.h1`
  color: white;
`;

const ModalFrame = ({ showModal, _handleModal, children, ...rest }) => {
  if (!showModal) {
    return null;
  }

  return (
    <Container>
      <Background onClick={_handleModal} />
      <ModalBlock {...rest}>
        <Close src={close} onClick={_handleModal}></Close>
        <Contents>{children}</Contents>
      </ModalBlock>
    </Container>
  );
};

export default ModalFrame;
