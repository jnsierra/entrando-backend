package co.com.entrando.business.service.impl;

import co.com.entrando.business.service.QRGeneratorService;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Service
@Slf4j
public class QRGeneratorServiceImpl implements QRGeneratorService {
    @Value("${qr.path}")
    private String pathQRs;
    private QRCodeWriter qRCodeWriter;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private Function<String, BitMatrix> functionBitMatrix = confirmationCode -> {
        try {
            return this.qRCodeWriter.encode(confirmationCode, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        } catch (WriterException e) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al crear PNG QR", e);
        }
    };
    private BiPredicate<Path, BitMatrix> isCreated = (path, bitMatrix) -> {
        //Valido si el path existe

        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (IOException e) {
            return false;
        }
        return true;
    };
    private Function<String, Path> functionGeneratePath = confirmationCode ->  {
            String PATH = String.format("%s%s.png", pathQRs,confirmationCode);
            log.info("GENERATE-PATH-QR|{}", PATH);
            return FileSystems.getDefault().getPath(PATH);
    };
    @Autowired
    public QRGeneratorServiceImpl(QRCodeWriter qRCodeWriter) {
        this.qRCodeWriter = qRCodeWriter;
    }
    @Override
    public boolean generateQRCodeImage(String confirmationCode) {
        Path path = Paths.get(pathQRs);
        if(!Files.isDirectory(path)){
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, String.format("No existe el directorio %s base para el almacenamiento de los qrs contacte al administrador", pathQRs));
        }
        return isCreated.test(functionGeneratePath.apply(confirmationCode) ,functionBitMatrix.apply(confirmationCode));
    }
}