import Modal from "react-modal"

interface ProductModalProps {
    isOpen: boolean;
    onClose: () => void;
    children?: React.ReactNode;
}

const ProductModal: React.FC<ProductModalProps> = ({ isOpen, onClose, children }) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            contentLabel="Popup"
        >
            {children}
        </Modal>
    )
}

export default ProductModal;