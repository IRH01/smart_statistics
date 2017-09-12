package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.TerminalType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TerminalTypeReposity extends BaseRepository{
    public List<TerminalType> getAll(){
        List<TerminalType> values = template.selectList("terminalType.getAll");
        return values;
    }
}
